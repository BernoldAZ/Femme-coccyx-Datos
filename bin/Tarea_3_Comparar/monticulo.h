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
    void insert(int pValue);
    Node* eliminarMin();
    void eliminar(int pID);
    void print();
};

#endif // MONTICULO_H
