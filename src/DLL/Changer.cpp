#include "pch.h"
#include <Windows.h>
#include <stdio.h>
#include <iostream>
#include <ShObjIdl_core.h>
#include "Changer.h"


// function that actually does the work
// the ShObjIdl_core header file was a life saver 
// I couldn't get it to work with SystemsParamatersInfo it would just make my desktop black
void Java_dllFuncs_change_wallpaper() {
    std::wstring x = L"path to current.jpg";
    HRESULT ad;
    CoInitialize(NULL);

    IDesktopWallpaper* p;

    // does the actual changing
    if (SUCCEEDED(CoCreateInstance(__uuidof(DesktopWallpaper), 0, CLSCTX_LOCAL_SERVER, __uuidof(IDesktopWallpaper), (void**)&p))) {
        ad = p->SetWallpaper(NULL, x.c_str());
        p->Release();
    }

    CoUninitialize();
}
