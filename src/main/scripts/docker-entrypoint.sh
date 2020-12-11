#!/bin/bash

docker_entry_point() {
    cd /apps/spring-boot-demo/bin && /apps/spring-boot-demo/bin/spring-boot-demo $*
}

docker_entry_point $*