//
// Created by usuario on 20/11/2018.
//

#ifndef TAREA_3_COMPARAR_SAMPLEIMAGE_H
#define TAREA_3_COMPARAR_SAMPLEIMAGE_H


class SampleImage {
private:
    int ID;
    int PosX;
    int PosY;
    int Block;
    int RGB;
    int Repetitions;
public:
    SampleImage(int pID, int pPosX, int pPosY, int pBlock, int pRGB, int pRepetitions);
    int getID();



};


#endif //TAREA_3_COMPARAR_SAMPLEIMAGE_H
