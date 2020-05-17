cmake_minimum_required(VERSION 3.0.0)
project(nanodbc_java CXX)

# http://edsiper.linuxchile.cl/blog/2016/01/08/cmake-override-subdirectory-options/
macro(set_option option value)
  set(${option} ${value} CACHE INTERNAL "" FORCE)
endmacro()

set_option(NANODBC_DISABLE_ASYNC ON)
set_option(NANODBC_DISABLE_EXAMPLES ON)
set_option(NANODBC_DISABLE_INSTALL ON)
set_option(NANODBC_DISABLE_TESTS ON)

add_subdirectory(nanodbc)
add_subdirectory(nanodbc_ext)
