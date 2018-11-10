#include <iostream>
#include <string>
#include <sstream>
#include <arbolavl.h>
#include "monticulo.h"
#include "conectar.h"

using namespace std;

struct movies {
  std::string title;
  std::string genre;
  int year;
  double sales;
} deadpool, monja;



int main()
{
    ArbolAVL *arbol = new ArbolAVL();

    arbol->insert(&deadpool, 30);
    arbol->insert(&deadpool, 45);
    arbol->insert(&deadpool, 701);
    arbol->insert(&deadpool, 650);
    arbol->insert(&deadpool, 345);
    arbol->insert(&deadpool, 120);
    arbol->insert(&deadpool, 8);
    arbol->insert(&deadpool, 60);
    arbol->insert(&deadpool, 22);
    arbol->insert(&deadpool, 420);
    arbol->insert(&deadpool, 99);
    arbol->insert(&deadpool, 37);
    arbol->insert(&deadpool, 111);
    arbol->insert(&deadpool, 488);
    arbol->insert(&deadpool, 35);

    //arbol->getInOrder(true);
    //std::cout << "Ahora el monticulo" << std::endl;

    Monticulo *tarea = new Monticulo();

    tarea->insert(1);
    tarea->insert(2);
    tarea->insert(3);
    tarea->insert(4);
    tarea->insert(5);
    tarea->insert(6);
    tarea->insert(7);
    tarea->insert(8);
    tarea->insert(9);
    tarea->insert(10);
    tarea->insert(11);
    tarea->insert(12);

    tarea->print();



    return 0;

}
