#! /usr/bin/env groovy

// Common Settings

GIT_SERVER  = "git@git.linge.io"

BUILD_DIR   = "."

wrap([$class: 'BuildUser']) { BUILD_USER = BUILD_USER }


// SKIP 
SKIP_GIT    = false
SKIP_BUILD  = false
SKIP_DOCKER = false

// Project info
WEB_ROOT        = "/data/www/" + APP_NAME   // /data/www/demo-php

// Docker
DOCKER_IMAGE_NAME   = "img.linge.io/project/" + APP_NAME
// img.linge.io/project/demo-php

DOCKER_IMAGE_TAG    = SCM_REVISION.replace('/', '-')
// origin/master    -> origin-master

DOCKERFILE_IGNORE   = "$SCRIPTS/.dockerignore"

DOCKERFILE_DIR  = "/data/app/jenkins/files/dockerfiles/k8s/templates"
DOCKERFILE_LANG = "$DOCKERFILE_DIR/language/$DEV_LANG/Dockerfiles"


// Stage
STAGE_GIT       = "build"
STAGE_DOKCER    = "build"


Git         = load("$SCRIPTS/git.groovy")
Compile     = load("$SCRIPTS/compile.groovy")
Docker      = load("$SCRIPTS/docker.groovy")
Stages      = load("$SCRIPTS/stages.groovy")
