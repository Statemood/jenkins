# Jenkins shell

Jenkins Shell 工具集

## 运行环境:
    Linux/OS X


## 使用方法
1. git clone https://github.com/Statemood/jenkins.git
2. mv config /data/app/jenkins (如使用其他路径需要修改文件 bash-slice, settings)
3. Jenkins管理设置中配置 bash-slice 为默认Shell
4. 在 Jenkins 任务配置中 'Execute shell' Command 窗口中可以直接引用settings文件全部变量及函数 load
5. 使用 load base 方法载入modules/base 文件, 在后续命令中直接使用函数即可

# 变量

## 通用
#### 1. APP_PRJ
- 从JOB_NAME中以下划线分隔取第一列

#### 2. APP_SUB
- 从JOB_NAME中以下划线分隔取第二列

#### 3. APP_SUB_A
- 从APP_SUB中以减号分隔取第一列

#### 4. APP_SUB_B
- 从APP_SUB中以减号分隔取第二列

#### 5. APP_ENV
- 从JOB_NAME中以下划线分隔取第三列


## Build
#### 1. BUILD_USER
- Jenkins系统变量，用户全名

#### 2. BUILD_USER_ID
- Jenkins系统变量，用户名

## Docker

#### 1. APP_FILE
- 要复制到容器中的文件路径

#### 2. APP_NAME
- 项目名称，未定义则默认使用APP_SUB
- 在容器中
  - APPNAME 为全局变量
  - 作为项目名称

#### 3. APP_PORT
- 容器(内服务)运行端口
- 应用与容器中全局变量
- 替换 **Deployment & Service** 文件中端口

#### 4. DOCKER_BUILD_OPTS
- 提供 Docker Build 参数，默认为空

#### 5. TEMPLATE_COMMON
- 通用Dockerfiles文件路径

#### 6. TEMPLATE_SPECIFY
- 自定义Dockerfiles文件路径, 与项目匹配，如不存在则使用 TEMPLATE_COMMON

#### 7. DOCKERFILE_IGNORE
- Docker build 忽略文件

#### 8. DOCKER_TAG
- docker tag， 默认为 SCM_REVISION, 如最终为空则使用 latest


## Kubernetes
#### 1. LIMIT_MAX_CPU
- k8s 资源限制：最大CPU

#### 2. LIMIT_MIN_CPU
- k8s 资源限制：初始CPU

#### 3. LIMIT_MAX_MEM
- k8s 资源限制：最大内存

#### 4. LIMIT_MIN_MEM
- k8s 资源限制：初始内存

#### 5. LIVENESS_IDS
- k8s 健康检查：容器启动后延迟检查时间(S)

#### 6. LIVENESS_URL
- k8s 健康检查：URL

#### 7. LIVENESS_TIMEOUT
- k8s 健康检查：超时时间(S)

#### 8. READINESS_IDS
- 为空则使用 LIVENESS_IDS 数值

#### 9. READINESS_URL
- 为空则使用 LIVENESS_URL 数值





## ALLOW, 权限检查
#### 1. ALLOWED_USERS
- 用户权限检查，允许的用户，可以是多个，空格分隔
- 使用 BUILD_USER_ID
- 如 **ALLOWED_USERS="tom,jim**"

#### 2. ALWAYS_ALLOWED_GROUPS
- 用户权限检查，直接允许用户组
- 如 **ALWAYS_ALLOWED_GROUPS="test,ops,dba"**

##

## SKIP
#### 1. SKIP_DOCKER
- 等于 **true** 时，Docker 相关操作将被跳过

#### 2. SKIP_KUBE
- 等于 **true** 时，kubectl 相关操作将被跳过

#### 3. SKIP_MAVEN
- 等于 **true** 时，maven 相关操作将被跳过

#### 4. SKIP_SONARSCANNER
- 等于 **true** 时，SonarQube 相关操作将被跳过
