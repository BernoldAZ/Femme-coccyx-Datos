#include "node.h"
#include <sstream>
#include <iostream>
#include <string>


Node::Node(int pID)
{
    ID = pID;
    Hijo_Der = nullptr;
    Hijo_Izq = nullptr;

}
Node::Node(int pID, void *pValue){
    ID = pID;
    Value = pValue;
    Hijo_Der = nullptr;
    Hijo_Izq = nullptr;
           }


int Node:: getID() {
    return ID;
}

void Node::setHijo_Der(Node *pHijo_Der){
    Hijo_Der = pHijo_Der;
}

void Node::setHijo_Izq(Node *pHijo_Izq){
    Hijo_Izq = pHijo_Izq;
}

Node* Node::getHijo_Der(){
    return Hijo_Der;
}

Node* Node::getHijo_Izq(){
    return Hijo_Izq;
}

int Node::getBalanceo(){
    return balanceo;
}

void Node::setBalanceo(){
    //Falta implementar
}

void* Node::getValue(){
    return Value;
}

void Node::setArco(Node* pGrafo){
    arco = pGrafo;
}

Node* Node::getArco(){
    return arco;
}

void Node::setPeso(int pPeso){
    peso = pPeso;
}

int Node::getPeso(){
    return peso;
}
