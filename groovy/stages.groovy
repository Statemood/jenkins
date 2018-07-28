#! /usr/bin/env groovy

def Controller(){
    node(STAGE_GIT) {
        stage("Stage 1: Checkout Code"){
            sh("hostname")
            Git.Controller()
        }

    }
    node(DEV_LANG) {
        stage("Stage 2: Build $DEV_LANG") {
            Compile.Controller()
        }
    }

    node(STAGE_DOKCER) {
        stage("Stage 3: Docker") {
            Docker.Controller()
        }
    }
}

return this
