#include "monticulo.h"
#include <sstream>
#include <iostream>

Monticulo::Monticulo()
{
    last = 0;
    nodos[50];

}
int Monticulo::hijoMenor(int padre){
    if((padre * 2) + 1 >= last){
        return padre*2;
    }
    else{
        if(nodos[padre * 2]->getID() < nodos[(padre * 2) + 1]->getID()){
            return padre*2;
        }
        return (padre*2)+1;
    }

}


Node* Monticulo::eliminarMin(){
    Node* valorReturn = nodos[0];
    last--;
    nodos[0] = nodos[last]; //copia el ultimo elemento, al comienzo
    nodos[last] = 0; //Se borra lo que estaba en el ultimo elemento
    if (last > 1) {
        moverHaciaAbajo(0);
        return valorReturn;
    }
    else {
        return valorReturn;
    }
}


void Monticulo::moverHaciaAbajo(int posNodo){
    while (posNodo*2 < last ) {
        int posHijoMenor = hijoMenor(posNodo);
        if (nodos[posNodo]->getID() > nodos[posHijoMenor]->getID()) {
            Node* temp = nodos[posNodo];
            nodos[posNodo] = nodos[posHijoMenor];
            nodos[posHijoMenor] = temp;
        }
        posNodo = posHijoMenor;
    }

}

void Monticulo::moverHaciaArriba(int posNodo){
    while(posNodo / 2 > 0){
        if(nodos[posNodo]->getID() <= nodos[posNodo/2]->getID()){
            Node* temp = nodos[posNodo/2];
            nodos[posNodo/2] = nodos[posNodo];
            nodos[posNodo] = temp;
        }
        posNodo = posNodo /2;

    }
    if(nodos[posNodo]->getID() <= nodos[posNodo/2]->getID()){
        Node* temp = nodos[posNodo/2];
        nodos[posNodo/2] = nodos[posNodo];
        nodos[posNodo] = temp;
    }

}

Node* Monticulo::buscar(int pID){
    int contador = 0;
    while (contador < last) {
        if (nodos[contador]->getID() == pID) {
            return nodos[contador];
        }
        contador++;
    }
    return 0;


}

void Monticulo::insert(int pValue){
    if (last != 0) {
        Node *newNode = new Node(pValue);
        nodos[last] = newNode;
        moverHaciaArriba(last);
        last++;
        nodos[last] = 0;
        return;

    }
    Node *newNode = new Node(pValue);
    nodos[last] = newNode;
    last++;
    nodos[last] = 0;
    return;
}

void Monticulo::print(){
    for (int pos = 0; pos < last; pos++) {
        std::cout << nodos[pos]->getID() << std::endl;
    }
}
