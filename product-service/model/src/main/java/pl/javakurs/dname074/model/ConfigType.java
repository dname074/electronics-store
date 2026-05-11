package pl.javakurs.dname074.model;

enum ConfigType {
    MOTHERBOARD_CHIPSET, MOTHERBOARD_SOCKET, MOTHERBOARD_PCIE_GEN,
    RAM_TYPE, RAM_AMOUNT,
    CPU, CPU_PRODUCER, CPU_GENERATION, CPU_CORES, CPU_THREADS,
    DISK_TYPE, DISK_CAPACITY,
    GPU_MODEL, GPU_TYPE,
    CHARGER_POWER, BATTERY,
    COOLING, COLOR, BRAND, MODEL, RELEASE_DATE,
    OS,  // (np. Windows, Linux, Android)
    SCREEN_DIAGONAL, SCREEN_RESOLUTION, SCREEN_CAMERA, SCREEN_HERC,
    COMMUNICATION, // (np. bluetooth, Wi-fi)
    WEIGHT, HEIGHT, WIDTH, // calego sprzetu
    PORTS, // (usb-c, HDMI, displayport itd.)
    CASE, //(obudowa komputera)
    MARKETING // (np. najczęściej wybierane wśród takich i takich klientów)
}