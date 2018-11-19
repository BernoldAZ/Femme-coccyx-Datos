cmake_minimum_required(VERSION 3.12)
project(Tarea_3_Comparar)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(Tarea_3_Comparar
        arbolavl.cpp
        arbolavl.h
        conectar.cpp
        conectar.h
        main.cpp
        monticulo.cpp
        monticulo.h
        node.cpp
        node.h JavaConection.cpp JavaConection.h)
