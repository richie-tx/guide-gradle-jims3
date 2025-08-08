/*
 * Created on Jun 20, 2006
 *
 */
package mojo.tools.code.audit.checkstyle.check;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * @author Jim Fisher
 *
 */
public class PartitionCheck extends Check
{
    public int[] getDefaultTokens()
    {
        return new int[]
        { TokenTypes.PACKAGE_DEF, TokenTypes.IMPORT };
    }

    public void visitToken(DetailAST aAST)
    {
        // find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
        final DetailAST nameAST = aAST.getLastChild().getPreviousSibling();

        final FullIdent full = FullIdent.createFullIdent(nameAST);

        String pkgName = full.getText();
    }
}
