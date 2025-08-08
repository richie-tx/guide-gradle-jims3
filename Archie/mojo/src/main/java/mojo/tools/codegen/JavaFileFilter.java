package mojo.tools.codegen;

import java.io.File;
import java.io.FileFilter;

public class JavaFileFilter implements FileFilter
{
	public boolean accept(File f)
	{
		return f.isDirectory() || f.getName().toLowerCase().endsWith(".java");
	}
}
