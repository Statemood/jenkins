# Jenkins

Jenkins Shell 工具集
***
###运行环境:
    Linux/OS X


###使用方法
    1. git clone https://github.com/Statemood/jenkins.git
    2. mv config /data/app/jenkins (如使用其他路径需要修改文件 bash-slice, settings)
    3. Jenkins管理设置中配置 bash-slice 为默认Shell
    4. 在 Jenkins 任务配置中 'Execute shell' Command 窗口中可以直接引用settings文件全部变量及函数 load
    5. 使用 load base 方法载入modules/base 文件, 在后续命令中直接使用函数即可
        `load base
         load file`
