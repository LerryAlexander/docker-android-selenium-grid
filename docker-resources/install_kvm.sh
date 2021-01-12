#!/bin/bash

echo "Installing KVM on "$DOCKER_EMULATOR_SAMSUNG_S6
docker exec $DOCKER_EMULATOR_SAMSUNG_S6 sh -c "apt-get update -y
apt-get install qemu -y
qemu-system-x86_64
qemu-system-x86_64 \
  -append 'root=/dev/vda console=ttyS0' \
  -drive file='rootfs.ext2.qcow2,if=virtio,format=qcow2'  \
  -enable-kvm \
  -kernel 'bzImage' \
  -nographic \
;"

echo "Installing KVM on "$DOCKER_EMULATOR_SAMSUNG_S10
docker exec $DOCKER_EMULATOR_SAMSUNG_S10 sh -c "apt-get update -y
apt-get install qemu -y
qemu-system-x86_64
qemu-system-x86_64 \
  -append 'root=/dev/vda console=ttyS0' \
  -drive file='rootfs.ext2.qcow2,if=virtio,format=qcow2'  \
  -enable-kvm \
  -kernel 'bzImage' \
  -nographic \
;"
