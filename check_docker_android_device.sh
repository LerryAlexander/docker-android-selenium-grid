#!/usr/bin/env bash
if adb devices | grep -q 'emulator-5554'; then
  echo "emulator device found successfuly"
else
  echo "emulator device not found"
  exit 1
fi