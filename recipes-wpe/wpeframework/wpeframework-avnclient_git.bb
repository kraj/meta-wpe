SUMMARY = "WPE Framework AVN Client Plugin"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "CLOSED"

DEPENDS = "wpeframework avn-client"

PV = "3.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/WebPlatformForEmbedded/WPEPluginAVNClient.git;protocol=ssh;branch=master"

SRCREV = "2cc90668e186847d9ddd9a19fa15fd8752884877"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[debug] = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"

# ----------------------------------------------------------------------------

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/wpeframework/plugins/*.so ${datadir}/WPEFramework/*"
FILES_${PN}-dbg += "${libdir}/wpeframework/plugins/.debug/*.so"

# ----------------------------------------------------------------------------

TOOLCHAIN = "gcc"
