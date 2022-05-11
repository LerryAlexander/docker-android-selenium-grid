#!/bin/bash
curl -o src/get-pip.py https://bootstrap.pypa.io/pip/3.6/get-pip.py -o get-pip.py \
&& apt-get install python3-distutils -y \
&& apt-get install python3-apt \
&& python3 get-pip.py \
&& pip install toml \
&& python3 -m src.app