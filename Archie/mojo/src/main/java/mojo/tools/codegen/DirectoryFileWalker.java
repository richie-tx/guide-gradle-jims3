package mojo.tools.codegen;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;

public class DirectoryFileWalker extends DirectoryWalker
{
	private FileFilter filter;

	public DirectoryFileWalker(FileFilter aFilter)
	{
		super();
		this.filter = aFilter;
	}

	public void handleFile(File aFile, int aDepth, Collection aResults) throws IOException
	{
		if (filter != null)
		{
			if (filter.accept(aFile))
			{
				aResults.add(aFile);
			}
		}
		else
		{
			aResults.add(aFile);
		}
	}

	public void start(File startDir, List results) throws IOException
	{
		super.walk(startDir, results);
	}
}