//
// Created by usuario on 20/11/2018.
//

#include "SampleImage.h"

SampleImage::SampleImage(int pID, int pPosX, int pPosY, int pBlock, int pRGB, int pRepetitions) {
    ID = pID;
    PosX = pPosX;
    PosY = pPosY;
    Block = pBlock;
    RGB = pRGB;
    Repetitions = pRepetitions;
}

int SampleImage::getID() {
    return ID;
}
