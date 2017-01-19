package controller.commands;

import controller.SokobanController;

public interface SokobanCommand extends ICommand {
	void setParams(SokobanController sokoController,String input);
}
