#ifndef NODE_H
#define NODE_H




class Node
{
private:
    void *Value;
    int ID;
    int balanceo;
    Node *Hijo_Izq;
    Node *Hijo_Der;
    int peso;
    Node* arco;


public:
    Node(int pID);
    Node(int pID, void *pValue);
    int getID();
    Node* getHijo_Izq();
    Node* getHijo_Der();
    void setHijo_Izq(Node *pHijo_Izq);
    void setHijo_Der(Node *pHijo_Der);
    int getBalanceo();
    void setBalanceo();
    void* getValue();

    void setArco(Node* pGrafo);
    Node* getArco();

    void setPeso(int Peso);
    int getPeso();
};


#endif // NODE_H
