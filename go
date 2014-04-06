#!/bin/sh

set -e

time vagrant ssh -c "cd /vagrant && `echo $@`"