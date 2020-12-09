#include "pch.h"
#include <Windows.h>
#include <stdio.h>
#include <iostream>
#include <ShObjIdl_core.h>
#include "Changer.h"


void Java_dllFuncs_change_wallpaper() {
    std::wstring x = L"PATH TO IMAGE FILE";
    HRESULT ad;
    CoInitialize(NULL);

    IDesktopWallpaper* p;

    if (SUCCEEDED(CoCreateInstance(__uuidof(DesktopWallpaper), 0, CLSCTX_LOCAL_SERVER, __uuidof(IDesktopWallpaper), (void**)&p))) {
        ad = p->SetWallpaper(NULL, x.c_str());
        p->Release();
    }

    CoUninitialize();
}
