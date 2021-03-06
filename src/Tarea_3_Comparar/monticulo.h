#ifndef MONTICULO_H
#define MONTICULO_H
#include <node.h>

class Monticulo
{
    int hijoMenor(int padre);
    void moverHaciaArriba(int posNodo);
    void moverHaciaAbajo(int posNodo);
public:
    int last;
    Node* nodos[];
    Monticulo();
    Node* buscar(int pId);
    void insert(SampleImage *pValue ,int pID);
    Node* eliminarMin();
    void print();
};

#endif // MONTICULO_H
