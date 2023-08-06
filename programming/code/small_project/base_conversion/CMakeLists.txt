cmake_minimum_required(VERSION 3.10)

project(base_conversion C)

set(CMAKE_C_STANDARD 99)

add_executable(base_conversion main.c number.c)

include_directories(.)
