#! /bin/bash

msg (){
    echo "`date +'%F %T'` $1"
}

if [ -z "$@" ]
then
         dir=/opt
         pkj=$dir/$APPNAME/package.json
         dls="downloads.my.com"
    log_date="YYYY-MM-DD HH:mm Z"
     log_dir="/logs"
     log_out="$log_dir/${SUBNAME}_access.log"
     log_err="$log_dir/${SUBNAME}_error.log"
      nodeid="$HOSTNAME"

     host_ip="`hostname -i`"

    # Replace hosts file

    echo "192.168.2.19  $dls" >> /etc/hosts

    msg "Download hosts file: hosts.$ENV_NAME-$ENV_ID"
    curl -Lskj https://$dls/files/hosts/hosts.$ENV_NAME-$ENV_ID | cat >> /etc/hosts

    # Filebeat
    f="/etc/filebeat/filebeat-node.yml"
    sed -i "s/APP_NAME/$APPNAME/"   $f
    sed -i "s/APP_ENV/$ENV_NAME/"   $f
    sed -i "s/ENV_ID/$ENV_ID/"      $f

    sed -i "s/LOG_REDIS_SERVER/$LOG_REDIS_SERVER/"  $f

    test -d "$log_dir" || mkdir "$log_dir"

    msg "Start Filebeat"
    filebeat.sh --path.config /etc/filebeat -c $f &

    echo "{\"ServerID\": \"$APP_ENV-$ENV_ID\"}" > /opt/serverId.json
    msg "Generated serverId.json"

    grep -q "pm2 start" $pkj
    if [ $? = 0 ]
    then
        cd $dir/$APPNAME
        msg "Start npm with command: npm test, Port: $PORT"
        npm test
    else
        # Start with pm2
        msg "Start npm with pm2 start, Port: $PORT"
        pm2 start $dir/$APPNAME/www --name $APPNAME --no-daemon --merge-logs --log-date-format="$log_date" -o $log_out -e $log_err
    fi
else
    "$@"
fi
