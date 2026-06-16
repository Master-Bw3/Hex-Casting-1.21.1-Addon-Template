{ pkgs ? import <nixpkgs> {} }:

let
  libs = with pkgs; [
    libpulseaudio
    libGL
    glfw
    openal
    stdenv.cc.cc.lib
  ];
in pkgs.stdenv.mkDerivation {
  name = "hextemplate";

  src = ./.;

  nativeBuildInputs = with pkgs; [
    python313
    python313Packages.copier-template-tester
    python313Packages.pydantic
    python313Packages.pydantic-core
    copier
    git
  ] ++ libs;

  buildPhase = ''
    rm -rf ./.ctt
    ctt
  '';

  installPhase = "
    rm -rf ./.ctt/default/.venv
    mkdir -p $out
    cp -r ./.ctt/default $out
  ";

  LD_LIBRARY_PATH = pkgs.lib.makeLibraryPath libs;
}
