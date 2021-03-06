SUMMARY = "TI SCI firmware"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

DEPENDS = "openssl-native u-boot-mkimage-native dtc-native"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "k3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "7a6ce28348a99d2ad08a6cb2b36450d73bfccc80"

BRANCH ?= "ti-linux-firmware-4.1.y"

SRC_URI = " \
	git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH} \
	git://git.ti.com/processor-firmware/system-firmware-image-gen.git;protocol=git;branch=master;destsuffix=imggen;name=imggen \
"

SRCREV_imggen = "3cbf4003060ead508653e2ec8f25c59bbe813e74"
SRCREV_FORMAT = "imggen"

S = "${WORKDIR}/git"

SYSFW_TISCI_am65xx-evm = "ti-sci-firmware-am65x-gp.bin"
SYSFW_BINARY = "sysfw.itb"
SYSFW_IMAGE = "sysfw-${PV}.itb"

CROSS_COMPILE_V7 = "${ELT_TARGET_SYS_ARMV7}-"
PATH_prepend = "${TOOLCHAIN_PATH_ARMV7}/bin:"

do_compile() {
	cp ${S}/ti-sysfw/${SYSFW_TISCI} ${WORKDIR}/imggen/
	cd ${WORKDIR}/imggen/
	oe_runmake SYSFW_DL_URL=""
}

do_install() {
	install -d ${D}/boot
	install -m 644 ${WORKDIR}/imggen/${SYSFW_BINARY} ${D}/boot/${SYSFW_IMAGE}
	ln -sf ${SYSFW_IMAGE} ${D}/boot/${SYSFW_BINARY}
}

FILES_${PN} = "/boot"

inherit deploy

do_deploy () {
	install -d ${DEPLOYDIR}
	install -m 644 ${WORKDIR}/imggen/${SYSFW_BINARY} ${DEPLOYDIR}/${SYSFW_IMAGE}
	rm -f ${DEPLOYDIR}/${SYSFW_BINARY}
	ln -sf ${SYSFW_IMAGE} ${DEPLOYDIR}/${SYSFW_BINARY}

        install -m 644 ${S}/ti-sysfw/${SYSFW_TISCI} ${DEPLOYDIR}/${SYSFW_TISCI}
}

addtask deploy before do_build after do_compile
