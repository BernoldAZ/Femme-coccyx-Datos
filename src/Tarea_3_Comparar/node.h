#ifndef NODE_H
#define NODE_H


#include "SampleImage.h"

class Node
{
private:
    SampleImage *Value;
    int ID;
    Node *Hijo_Izq;
    Node *Hijo_Der;
    int peso;
    Node* arco;


public:
    Node(int pID, SampleImage *pValue);
    int getID();
    Node* getHijo_Izq();
    Node* getHijo_Der();
    void setHijo_Izq(Node *pHijo_Izq);
    void setHijo_Der(Node *pHijo_Der);
    SampleImage* getValue();

    void setArco(Node* pGrafo);
    Node* getArco();

    void setPeso(int Peso);
    int getPeso();
};


#endif // NODE_H
