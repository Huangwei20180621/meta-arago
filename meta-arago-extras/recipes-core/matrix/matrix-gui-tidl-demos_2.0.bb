DESCRIPTION = "Deep learning vision demo descriptions for Matrix v2"
HOMEPAGE = "http://git.ti.com/matrix-gui-v2/matrix-gui-v2-apps"

require matrix-gui-apps-git.inc

PR = "${INC_PR}.0"

inherit allarch

S = "${WORKDIR}/git/tidl_apps"

# Make sure tidl submenu and app images has been installed
TIDLDEMO_RDEPENDS += "matrix-gui-apps-images matrix-gui-submenus-tidl"

FILES_${PN} += "${MATRIX_BASE_DIR}/*"

PACKAGES = "matrix-tidl-demo-staticimg \
            matrix-tidl-demo-pnexamples \
            matrix-tidl-demo-livecam \
"

RDEPENDS_matrix-tidl-demo-staticimg = " \
    ${TIDLDEMO_RDEPENDS} \
"

RDEPENDS_matrix-tidl-demo-pnexamples = " \
    ${TIDLDEMO_RDEPENDS} \
"

RDEPENDS_matrix-tidl-demo-livecam = " \
    ${TIDLDEMO_RDEPENDS} \
"

FILES_matrix-tidl-demo-staticimg = "${MATRIX_APP_DIR}/tidl_demo_staticimg/*"
FILES_matrix-tidl-demo-staticimg += "${bindir}/runTidlStaticImg.sh"

FILES_matrix-tidl-demo-pnexamples = "${MATRIX_APP_DIR}/tidl_demo_pnexamples/*"
FILES_matrix-tidl-demo-pnexamples += "${bindir}/runTidlPnExamples.sh"

FILES_matrix-tidl-demo-livecam   = "${MATRIX_APP_DIR}/tidl_demo_livecam/*"
FILES_matrix-tidl-demo-livecam   += "${bindir}/runTidlLiveCam.sh"
