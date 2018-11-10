#include "conectar.h"
#include <iostream>
#include <string>
#include <sstream>
Conectar::Conectar()
{

}

void CompararNodoConMonticulo (Node *pNode, Monticulo *pMonticulo, int posEnAVL){
    for (int PosActual = 0;PosActual < pMonticulo->last ; PosActual++) {
        if (pNode->getID() == pMonticulo->nodos[PosActual]->getID()) {
            Node* puntero = pMonticulo->nodos[PosActual];
            pNode->setArco(puntero);
            pNode->setPeso( abs(posEnAVL - PosActual));
            return;
        }
    }
    return;
}

void Recorrer(Node *pNode, Monticulo *pMonticulo, int Pos){
    if (pNode == nullptr){
        return;
    }
    else {
        CompararNodoConMonticulo(pNode, pMonticulo, Pos);
        Recorrer( pNode->getHijo_Izq(), pMonticulo, Pos++);

        Recorrer( pNode->getHijo_Der(), pMonticulo, Pos++);

    }
}

void Conectar::comparar(ArbolAVL* pArbol, Monticulo* pMont){
    Recorrer(pArbol->getRaiz(),pMont, 0 );
}

