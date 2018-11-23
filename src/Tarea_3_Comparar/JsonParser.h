//
// Created by Esteban Madrigal on 11/23/2018.
//

#ifndef TAREA_3_COMPARAR_JSONPARSER_H
#define TAREA_3_COMPARAR_JSONPARSER_H


#include <string>
#include "monticulo.cpp"
#include "arbolavl.h"

class JsonParser {
public:
    ArbolAVL *arbol;
    Monticulo *montiuclo;
    JsonParser(std::string content);
};


#endif //TAREA_3_COMPARAR_JSONPARSER_H
