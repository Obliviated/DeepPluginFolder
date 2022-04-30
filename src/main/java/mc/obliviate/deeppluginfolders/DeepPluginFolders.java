package mc.obliviate.deeppluginfolders;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class DeepPluginFolders extends JavaPlugin {

	@Override
	public void onLoad() {
		Bukkit.getLogger().info("loading process started");
		File pluginsFolder = new File("plugins");
		loadPluginsFoldersIn(pluginsFolder, false);
		Bukkit.getLogger().info("loading process finished");
	}

	public void loadPluginsFoldersIn(File pluginsFile, boolean loadItSelf) {
		if (pluginsFile == null)
			throw new IllegalArgumentException("plugin folder could not loaded because it was null.");
		if (pluginsFile.listFiles() == null || pluginsFile.listFiles().length == 0) return;

		for (File file : pluginsFile.listFiles()) {
			if (file.getName().startsWith("_") && !file.getName().endsWith(".jar")) {
				loadPluginsFoldersIn(file, true);
			}
		}

		if (loadItSelf) {
			Bukkit.getLogger().info("plugins loading from: " + pluginsFile.getPath());
			Bukkit.getPluginManager().loadPlugins(pluginsFile);
		}
	}




}
