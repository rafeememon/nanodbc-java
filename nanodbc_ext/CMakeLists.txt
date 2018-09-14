cmake_minimum_required(VERSION 3.0.0)
project(nanodbc_ext CXX)

add_library(nanodbc_ext nanodbc_ext.cpp nanodbc_ext.h)
include_directories(${nanodbc_SOURCE_DIR})
target_link_libraries(nanodbc_ext nanodbc)
