#ifndef ARBOLAVL_H
#define ARBOLAVL_H
#include <node.h>

class ArbolAVL
{
private:
    Node *Raiz;
    bool vacio = true;
    void getInOrdenArbol(Node *pNode, int pFlag);

    void hacerBalanceo(Node* raiz, Node* ElDeArriba);
public:
    ArbolAVL();
    Node* getRaiz ();
    void setAltura();

    void insert(void *pValue, int pID);
    void deleteNode(int pID);
    bool IsEmpty();
    Node getLeft();
    Node getRight();
    void getInOrder(bool pDebug);
    Node buscar(int pId);


};
#endif // ARBOLAVL_H
