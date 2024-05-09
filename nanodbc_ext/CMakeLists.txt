cmake_minimum_required(VERSION 3.0.0)
project(nanodbc_ext CXX)

add_library(nanodbc_ext nanodbc_ext.cpp nanodbc_ext.h)
include_directories(${nanodbc_SOURCE_DIR})
target_link_libraries(nanodbc_ext nanodbc)

# http://edsiper.linuxchile.cl/blog/2016/01/08/cmake-override-subdirectory-options/
macro(set_option option value)
  set(${option} ${value} CACHE INTERNAL "" FORCE)
endmacro()

set_option(NANODBC_DISABLE_ASYNC ON)
set_option(NANODBC_DISABLE_EXAMPLES ON)
set_option(NANODBC_DISABLE_INSTALL ON)
set_option(NANODBC_DISABLE_TESTS ON)
set_option(NANODBC_ENABLE_UNICODE ON)
