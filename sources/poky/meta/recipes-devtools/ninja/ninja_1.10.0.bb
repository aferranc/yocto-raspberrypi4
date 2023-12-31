SUMMARY = "Ninja is a small build system with a focus on speed."
HOMEPAGE = "https://ninja-build.org/"
DESCRIPTION = "Ninja is a small build system with a focus on speed. It differs from other build systems in two major respects: it is designed to have its input files generated by a higher-level build system, and it is designed to run builds as fast as possible."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=a81586a64ad4e476c791cda7e2f2c52e"

DEPENDS = "re2c-native ninja-native"

SRCREV = "ed7f67040b370189d989adbd60ff8ea29957231f"

SRC_URI = "git://github.com/ninja-build/ninja.git;branch=release;protocol=https"
UPSTREAM_CHECK_GITTAGREGEX = "v(?P<pver>.*)"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"

do_compile_class-native() {
	python3 ./configure.py --bootstrap
}

do_compile() {
	python3 ./configure.py
	ninja
}

do_install() {
	install -D -m 0755  ${S}/ninja ${D}${bindir}/ninja
}

BBCLASSEXTEND = "native nativesdk"

# This is a different Ninja
CVE_CHECK_WHITELIST += "CVE-2021-4336"
