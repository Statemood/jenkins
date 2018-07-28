#! /usr/bin/env groovy

// 函数执行入口
// 通过 Git.Controller() 调用
def Controller(){
    // 如果SKIP_GIT = true 则忽略 git 部分
    if (SKIP_GIT == true) {
        echo "Git skipped"
        return
    }
    // 初始化
    Init()

    // 获取代码
    Checkout()
}

// 当任务第一次执行时，设置git地址，获取分支和标签信息
def Init(){
    // 如果 GIT_REPO 为空，则报错终止
    if (! GIT_REPO) {
        error "GIT_REPO undefined"
    }

    if (GIT_REPO.find('git@') || GIT_REPO.find('^https://') || GIT_REPO.find('^http://')) {
        // https://github.com/Statemood/php.git
        GIT_URL = GIT_REPO
    } else {
        // git@git.linge.io:demo/php.git
        GIT_URL = GIT_SERVER + ':' + GIT_REPO
    }

    /* 如果当前路径下不存在 '.git' 目录，则执行:
        git url: GIT_REPO
       并在完成后终止任务
    */
    if (! fileExists('.git')){
        echo "First run, init git"
    
        git url: GIT_URL

        error "Init done. Please rebuild job"
    }
}

// 获取代码
def Checkout() {
    // 输出两条提示
    echo "git is " + GIT_URL
    echo "revision is " + SCM_REVISION
    
    // 判断 SCM_REVISION 是否为空，空则报错终止任务
    if (! SCM_REVISION) {
        error "No branch or tag selected"
    }
    
    // 使用 checkout 方法获取指定版本代码
    checkout([$class: 'GitSCM',
              branches: [[name: SCM_REVISION]],
              userRemoteConfigs: [[url: GIT_URL]]])
    
    echo "git pull finished"
}

return this
