tasks.register("installGitHooks", Copy::class.java) {
    from(File(rootProject.rootDir, "tools/hooks/pre-commit"))
    into(File(rootProject.rootDir, ".git/hooks"))
    fileMode = 0b0111101101 // -rwxr-xr-x
}
