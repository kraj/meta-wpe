SUMMARY = "AV Nano Client"
DEPENDS += "userland libpng gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-omx glib-2.0"
LICENSE = "CLOSED"

SRCREV = "d7b8a55cba8e7c30bbd35eccbe17c5b2ef8c4c10"

SRC_URI = "git://git@github.com/Metrological/avn-nanoclient.git;protocol=ssh;branch=avn_so"
S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF = "'CC=${CC}' 'CXX=${CXX}' 'CFLAGS=${CFLAGS}' 'CXXFLAGS=${CXXFLAGS}' 'CPPFLAGS=${CPPFLAGS}' 'LDFLAGS=${LDFLAGS}'"

PACKAGECONFIG ??= ""
PACKAGECONFIG[avnapp]  = ",,"

do_compile () {
    if ${@bb.utils.contains("PACKAGECONFIG", "avnapp", "true", "false", d)} 
    then
        export AVNCLIENT_APP=true
    fi
    export SYSROOTPATH=${STAGING_DIR_TARGET}
    ${MAKE} -C ${S} clean
    ${MAKE} -C ${S}
}

do_install () {
    if ${@bb.utils.contains("PACKAGECONFIG", "avnapp", "true", "false", d)}  
    then
        install -d ${D}/usr/bin
        install -m 0755 ${S}/output/linux/release/bin/mclient ${D}/usr/bin
    else
        install -d ${D}/usr/lib
        install -m 0755 ${S}/output/linux/release/lib/libcloudtv_mclient.so ${D}/usr/lib
    fi
}

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so ${bindir}/*"
INSANE_SKIP ="dev-so dev-deps ldflags"
