#! /usr/bin/env groovy

def Controller(){
    if (SKIP_BUILD == true) {
        echo "Build skipped"
        
        return
    }

    dir(BUILD_DIR) {
        if (DEV_LANG == "php" && fileExists("composer.json")) {
            echo "Build $DEV_LANG"
            sh("composer install")
        }
    }
}

return this
