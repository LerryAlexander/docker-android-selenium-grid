#!/bin/bash

  healthy_status=false
  docker exec $DOCKER_EMULATOR_SAMSUNG_S6 /root/emulator/emulator -avd Samsung Galaxy S6
  docker exec $DOCKER_EMULATOR_SAMSUNG_S6 /root/emulator/emulator -list-avds
  docker exec $DOCKER_EMULATOR_SAMSUNG_S10 /root/emulator/emulator -avd Samsung Galaxy S10
  docker exec $DOCKER_EMULATOR_SAMSUNG_S10 /root/emulator/emulator -list-avds
  while [ "$healthy_status" == false ]; do
    statusS6=$(docker inspect --format='{{json .State.Health}}' samsung_s6_container | grep -n '"Status":"healthy"')
    statusS10=$(docker inspect --format='{{json .State.Health}}' samsung_s10_container | grep -n '"Status":"healthy"')
    if [ "$statusS6" != "" ] && [ "$statusS10" != "" ]; then
      healthy_status=true
      echo "emulators are ready"
      echo "executing tests..."
    else
      echo "waiting for emulators to be ready"
      docker ps -a
      sleep 1
    fi
  done