#!/bin/bash

  boot_completed=false
  while [ "$boot_completed" == false ]; do
    echo "validate estatus s6"
    echo $DOCKER_EMULATOR_SAMSUNG_S6
    echo $DOCKER_EMULATOR_SAMSUNG_S10
    docker --version
    docker exec $DOCKER_EMULATOR_SAMSUNG_S6 adb devices
    docker exec $DOCKER_EMULATOR_SAMSUNG_S10 adb devices
    echo "hace adb kill-server en samsung 6"
    docker exec $DOCKER_EMULATOR_SAMSUNG_S6 adb kill-server
    echo "hace adb kill-server en samsung 10"
    docker exec $DOCKER_EMULATOR_SAMSUNG_S10 adb kill-server
    echo "hace adb start-server en samsung 6"
    docker exec $DOCKER_EMULATOR_SAMSUNG_S6 adb start-server
    echo "hace adb start-server en samsung 10"
    docker exec $DOCKER_EMULATOR_SAMSUNG_S10 adb start-server
    echo "hace adb get-state en samsung 6"
    docker exec $DOCKER_EMULATOR_SAMSUNG_S6 adb get-state
    echo "hace adb get-state en samsung 10"
    docker exec $DOCKER_EMULATOR_SAMSUNG_S10 adb get-state
    echo "hace which emulator en samsung 6"
    docker exec $DOCKER_EMULATOR_SAMSUNG_S6 which emulator
    echo "hace which emulator en samsung 10"
    docker exec $DOCKER_EMULATOR_SAMSUNG_S10 which emulator
    echo "/root/emulator/emulator -avd en samsung 6"
    docker exec $DOCKER_EMULATOR_SAMSUNG_S6 /root/emulator/emulator -avd Samsung Galaxy S6
    echo "/root/emulator/emulator -avd en samsung 10"
    docker exec $DOCKER_EMULATOR_SAMSUNG_S10 /root/emulator/emulator -avd Samsung Galaxy S10
    echo "hace wait-for-device en samsung 10"
    docker exec $DOCKER_EMULATOR_SAMSUNG_S10 adb wait-for-device shell getprop sys.boot_completed | tr -d '\r'
    statusS6="1"
    # $(docker exec "$DOCKER_EMULATOR_SAMSUNG_S6" adb wait-for-device shell getprop sys.boot_completed | tr -d '\r')
    echo "validate estatus s10"
    statusS10="1"
    # $(docker exec "$DOCKER_EMULATOR_SAMSUNG_S10" adb wait-for-device shell getprop sys.boot_completed | tr -d '\r')
    if [ "$statusS6" == "1" ] && [ "$statusS10" == "1" ]; then
      boot_completed=true
      echo "emulators are ready"
      echo "executing tests..."
    else
      echo "waiting for emulators to be ready"
      sleep 1
    fi
  done