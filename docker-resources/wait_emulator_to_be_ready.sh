#!/bin/bash

  boot_completed=false
  while [ "$boot_completed" == false ]; do
    status=$(docker exec $DOCKER_EMULATOR_HOSTNAME adb wait-for-device shell getprop sys.boot_completed | tr -d '\r')
    if [ "$status" == "1" ]; then
      boot_completed=true
      echo "emulator is ready"
      echo "executing tests..."
    else
      echo "waiting for emulator to be ready"
      sleep 1
    fi
  done