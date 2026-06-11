let
  nixpkgsVer = "26.05";
  pkgs = import (fetchTarball "https://github.com/NixOS/nixpkgs/archive/${nixpkgsVer}.tar.gz") { config = {}; overlays = []; };
  libs = with pkgs; [
    libpulseaudio
    libGL
    glfw
    openal
    stdenv.cc.cc.lib
  ];
in pkgs.mkShell {
  name = "trickster";

  buildInputs = with pkgs; [
    uv
    git
    jetbrains.jdk-21
  ] ++ libs;

  LD_LIBRARY_PATH = pkgs.lib.makeLibraryPath libs;
}
