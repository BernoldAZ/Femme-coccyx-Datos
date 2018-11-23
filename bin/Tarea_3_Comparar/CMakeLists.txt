cmake_minimum_required(VERSION 3.12)
project(Tarea_3_Comparar)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)
include_directories(.idea)

add_executable(Tarea_3_Comparar
        .idea/codeStyles/Project.xml
        .idea/misc.xml
        .idea/modules.xml
        .idea/Tarea_3_Comparar.iml
        .idea/vcs.xml
        .idea/workspace.xml
		arbolavl.cpp
        arbolavl.h
        conectar.cpp
        conectar.h
        main.cpp
        monticulo.cpp
        monticulo.h
        node.cpp
        node.h
        Tarea_3_Comparar.pro
        Tarea_3_Comparar.pro.user)
