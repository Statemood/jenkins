#! /usr/bin/env groovy

def Controller(){
    if (SKIP_DOCKER == true) {
        echo "Docker skipped"
        
        return
    }

    DockerfileGenerate()
    ImageBuild()
    ImagePush()
}

def DockerfileGenerate(){
    if (fileExists(DOCKERFILE_IGNORE)) {
        sh("cp -rf $DOCKERFILE_IGNORE .")
    }

    if (fileExists(DOCKERFILE_LANG)) {
        echo "Use " + DOCKERFILE_LANG

        sh("cp -rfH $DOCKERFILE_LANG/* .")
    }

    DOCKER_LABEL  = "job.name=$JOB_NAME build.user=$BUILD_USER "
    DOCKER_LABEL += "scm.revision=$SCM_REVISION"

    sh("echo COPY . $WEB_ROOT               >> Dockerfile")
    sh("echo ENTRYPOINT [\\\"/run.sh\\\"]   >> Dockerfile")
}   

def ImagePush(){
    sh("sudo docker push $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG")
}

def ImageBuild(){
    if(fileExists("Dockerfile")) {
        sh("sudo docker build -t $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG .")
    }
}

return this
