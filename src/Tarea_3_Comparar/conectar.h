#ifndef CONECTAR_H
#define CONECTAR_H
#include "arbolavl.h"
#include "monticulo.h"
#include <stdlib.h>

class Conectar
{
public:
    Conectar();
    void comparar(ArbolAVL* pArbol, Monticulo* pMont);
};

#endif // CONECTAR_H
