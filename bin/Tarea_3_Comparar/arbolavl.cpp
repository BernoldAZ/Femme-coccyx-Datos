#include "arbolavl.h"
#include <iostream>
#include <string>
#include <sstream>

using namespace std;

ArbolAVL::ArbolAVL()
= default;

int getAltura(Node* raiz, int contador = 0){
    if (raiz != nullptr){
        return  max(getAltura(raiz->getHijo_Der(),contador++),getAltura(raiz->getHijo_Izq(),contador++));
    }
    else return contador;
}



Node* ArbolAVL::getRaiz(){
    return Raiz;
}

void ArbolAVL::hacerBalanceo(Node* raiz, Node* ElDeArriba){
    if (raiz != nullptr){
        if(raiz != Raiz){
            int balanceo = getAltura(raiz->getHijo_Izq(),1) - getAltura(raiz->getHijo_Der(),1);
            if(-2 <balanceo && balanceo<2){ //Si esta balanceado
                hacerBalanceo(raiz->getHijo_Der(), raiz);
                hacerBalanceo(raiz->getHijo_Izq(), raiz);
                return;
            }
            else{//Se balanceo el arbol
                if (balanceo >= 2){ // Se hacen loc casos de left right y de left left.
                    if (raiz->getHijo_Izq()->getHijo_Der() != nullptr){
                        if(ElDeArriba->getHijo_Der()== raiz){
                            //Es el caso de left right pero se agrega al lado derecho del padre
                            Node* NuevoPadre = raiz->getHijo_Izq()->getHijo_Der();
                            ElDeArriba->setHijo_Der(NuevoPadre);
                            raiz->getHijo_Izq()->setHijo_Der(NuevoPadre->getHijo_Izq());
                            NuevoPadre->setHijo_Izq(raiz->getHijo_Izq());
                            raiz->setHijo_Izq(NuevoPadre->getHijo_Der());
                            NuevoPadre->setHijo_Der(raiz);
                            return;
                        }
                        //Es el caso de left right pero se agrega al lado izquierdo del padre
                        Node* NuevoPadre = raiz->getHijo_Izq()->getHijo_Der();
                        ElDeArriba->setHijo_Izq(NuevoPadre);
                        raiz->getHijo_Izq()->setHijo_Der(NuevoPadre->getHijo_Izq());
                        NuevoPadre->setHijo_Izq(raiz->getHijo_Izq());
                        raiz->setHijo_Izq(NuevoPadre->getHijo_Der());
                        NuevoPadre->setHijo_Der(raiz);
                        return;
                    }
                    else {
                        // Es el caso de left left
                        Node* NuevoPadre = raiz->getHijo_Izq();
                        ElDeArriba->setHijo_Izq(NuevoPadre);
                        raiz->setHijo_Izq(NuevoPadre->getHijo_Der());
                        NuevoPadre->setHijo_Der(raiz);
                        return;
                    }
                }
                else{ // Se hacen loc casos de right left y de right right.
                    if (raiz->getHijo_Der()->getHijo_Izq() != nullptr){
                        //Es el caso de right left
                        Node* NuevoPadre = raiz->getHijo_Der()->getHijo_Izq();
                        ElDeArriba->setHijo_Der(NuevoPadre);
                        raiz->getHijo_Der()->setHijo_Izq(NuevoPadre->getHijo_Der());
                        NuevoPadre->setHijo_Der(raiz->getHijo_Der());
                        raiz->setHijo_Der(NuevoPadre->getHijo_Izq());
                        NuevoPadre->setHijo_Izq(raiz);
                        return;
                    }
                    else{
                        //Es el caso de right right
                        Node* NuevoPadre = raiz->getHijo_Der();
                        ElDeArriba->setHijo_Der(NuevoPadre);
                        raiz->setHijo_Der(NuevoPadre->getHijo_Izq());
                        NuevoPadre->setHijo_Izq(raiz);
                        return;
                    }

                }
            }
        }
        else{
            hacerBalanceo(raiz->getHijo_Der(), raiz);
            hacerBalanceo(raiz->getHijo_Izq(), raiz);
        }
    }

}


bool ArbolAVL::IsEmpty(){
    return vacio;
}

void ArbolAVL:: insert(SampleImage *pValue, int pID){
    Node *newNode = new Node(pID, pValue);
    if (!(IsEmpty())){
        Node* temp = Raiz;
        while(temp != nullptr){
            if (newNode->getID() < temp->getID()){
                if (temp->getHijo_Izq() != nullptr){
                    temp = temp->getHijo_Izq();
                }
                else{
                    temp->setHijo_Izq(newNode);
                    //Hacer balanceo
                    hacerBalanceo(Raiz, Raiz);
                    return;
                }

             }
            else{
                if (temp->getHijo_Der() != nullptr){
                    temp = temp->getHijo_Der();
                }
                else{
                    temp->setHijo_Der(newNode);
                    //Hacer balanceo
                    hacerBalanceo(Raiz, Raiz);
                    return;
                }

            }

        }
    }
    else{
        Raiz = newNode;
        vacio = false;
        return;
    }
}

Node ArbolAVL:: getLeft(){
    if(!(IsEmpty())){
        Node *temp = Raiz;
        while (temp->getHijo_Izq() != nullptr){
                temp = temp->getHijo_Izq();
        }return *temp;
    }
    else{

    }
}

Node ArbolAVL:: getRight(){
    if(!(IsEmpty())){
        Node *temp = Raiz;
        while (temp->getHijo_Der() != nullptr){
            temp = temp->getHijo_Der();
        }return *temp;
    }
    else{

    }
}

Node ArbolAVL:: buscar(int pID){
    if(!(IsEmpty())){
        Node *temp = Raiz;
        for(int contador = 0 ; contador < getAltura(Raiz,1) ; contador++){
            if (pID == temp->getID()){
                return *temp;
            }
            else if(pID < temp->getID()){
                temp = temp->getHijo_Izq();
            }
            else{
                temp = temp->getHijo_Der();
            }
        }
    }

}

void ArbolAVL::getInOrder(bool pDebug){
    if(!(IsEmpty())){
        if (pDebug){
            return getInOrdenArbol(Raiz, 1);
        }
        return getInOrdenArbol(Raiz, 0);
    }
}

void ArbolAVL::getInOrdenArbol(Node *pNode, int pFlag){//Es una implementacion para el getInOrder, sirve para hacer la recursividad
    if (pNode == nullptr){
        return;
    }
    getInOrdenArbol( pNode->getHijo_Izq(), pFlag);
    if(pFlag==1){
        std::cout << pNode->getID() << std::endl;
    }
    getInOrdenArbol( pNode->getHijo_Der(), pFlag);
}

void ArbolAVL::deleteNode(int pID){
    if(!(IsEmpty())){
        Node* temp = Raiz;
        Node* padre;
        if(Raiz->getID() != pID){
            while(temp != nullptr){

                if (pID == temp->getID()){ //Encontro el que estaba buscando
                    Node* nodeADer = temp->getHijo_Der();

                    if(padre->getHijo_Izq() == temp){
                        if(temp->getHijo_Izq() != nullptr){ //Se caia si el hijo izquierdo era un nullptr, por eso fue que tuve que hacer este caso
                          Node* nodeAIzq = temp->getHijo_Izq();
                          padre->setHijo_Izq(nodeAIzq);
                          temp = temp->getHijo_Izq();
                          while (temp->getHijo_Der()!= nullptr){
                              temp = temp->getHijo_Der();
                          }
                          temp->setHijo_Der(nodeADer);
                          //Hacer balanceo
                          hacerBalanceo(Raiz, Raiz);
                          return;
                        }
                        padre->setHijo_Izq(nodeADer);
                        //Hacer balanceo
                        hacerBalanceo(Raiz, Raiz);
                        return;

                    }
                    else if(padre->getHijo_Der() == temp){
                        if(temp->getHijo_Izq() != nullptr){ // Lo mismo que en el anterior
                            Node* nodeAIzq = temp->getHijo_Izq();
                            padre->setHijo_Der(nodeAIzq);
                            temp = temp->getHijo_Izq();
                            while (temp->getHijo_Der()!= nullptr){
                                temp = temp->getHijo_Der();
                            }
                            temp->setHijo_Der(nodeADer);
                            //Hacer balanceo
                            hacerBalanceo(Raiz, Raiz);
                            return;
                        }
                        padre->setHijo_Der(nodeADer);
                        //Hacer balanceo
                        hacerBalanceo(Raiz, Raiz);
                        return;
                    }

                }

                else if(pID < temp->getID()){
                    padre = temp;
                    temp = temp->getHijo_Izq();
                }
                else{
                    padre = temp;
                    temp = temp->getHijo_Der();
                }
            }
            return; //Quiere decir que llego hasta el final y no encontro al nodo que se iba a eliminare
        }
        else{
            Node* nodeAInsertar = Raiz->getHijo_Der();
            Raiz = Raiz->getHijo_Izq();
            temp = Raiz;
            while (temp->getHijo_Der()!= nullptr){
                temp = temp->getHijo_Der();
            }
            temp->setHijo_Der(nodeAInsertar);
            //Hacer balanceo
            hacerBalanceo(Raiz, Raiz);
            return;
        }
    }

}

