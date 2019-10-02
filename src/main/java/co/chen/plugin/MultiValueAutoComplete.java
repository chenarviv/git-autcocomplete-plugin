package co.chen.plugin;

import co.chen.plugin.Terminal.GoodWindowExec;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.fileTypes.FileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.spellchecker.ui.SpellCheckingEditorCustomization;
import com.intellij.ui.EditorCustomization;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.EditorTextFieldProvider;
import com.intellij.ui.SoftWrapsEditorCustomization;
import com.intellij.util.TextFieldCompletionProvider;
import git4idea.GitUtil;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.SystemIndependent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MultiValueAutoComplete {
    private static final String[] SEPARATORS = {",", "\\n", "\\r", "\\r\\n"};


    public static EditorTextField create(Project project) {

/*
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("./gradlew");

        GeneralCommandLine generalCommandLine = new GeneralCommandLine(cmds);
        generalCommandLine.setCharset(Charset.forName("UTF-8"));
        generalCommandLine.setWorkDirectory(project.getBasePath());

        ProcessHandler processHandler = new OSProcessHandler(generalCommandLine);
        processHandler.startNotify();
*/






/*        @SystemIndependent String projectFilePath = project.getBasePath();
        String output = GoodWindowExec.execute("cd " + projectFilePath);

        String s = new File("").getAbsoluteFile().getAbsolutePath();
        System.out.println("Current relative path is: " + s);
        System.out.println("Output is:" + output);
        output = GoodWindowExec.execute("git add");
        System.out.println("Output now is:" + output);
        String output2 = output.replace('\r', '\n');
        System.out.println("Final output: " + project.getPresentableUrl());*/
        List<EditorCustomization> customizations =
                Arrays.<EditorCustomization>asList(SoftWrapsEditorCustomization.ENABLED, SpellCheckingEditorCustomization.DISABLED);
        EditorTextField editorField = ServiceManager.getService(project, EditorTextFieldProvider.class)
                .getEditorField(FileTypes.PLAIN_TEXT.getLanguage(), project, customizations);
        //   new CommaSeparatedTextFieldCompletion(project).apply(editorField);
      //  editorField.setText(output2);


        editorField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //  if (e.getKeyCode() == KeyEvent.VK_F4) {
           //     editorField.setText(output2);
                // GoodWindowExec.execute(editorField.getText());
                //   }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //   if (e.getKeyCode() == KeyEvent.VK_F4) {
         //       editorField.setText(output2);
                //  GoodWindowExec.execute(editorField.getText());
                //  }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F4) {
        //            editorField.setText(output2);
                    //GoodWindowExec.execute(editorField.getText());
                }

            }
        });
        return editorField;

    }

    private static class CommaSeparatedTextFieldCompletion extends TextFieldCompletionProvider {

        private Project project;

        CommaSeparatedTextFieldCompletion(Project project) {
            this.project = project;
        }

        @NotNull
        @Override
        protected String getPrefix(@NotNull String currentTextPrefix) {
            final int separatorPosition = lastSeparatorPosition(currentTextPrefix);
            return separatorPosition == -1 ? currentTextPrefix : currentTextPrefix.substring(separatorPosition + 1).trim();
        }

        private static int lastSeparatorPosition(@NotNull String text) {
            int lastPosition = -1;
            for (String separator : SEPARATORS) {
                int lio = text.lastIndexOf(separator);
                if (lio > lastPosition) {
                    lastPosition = lio;
                }
            }
            return lastPosition;
        }

        @Override
        protected void addCompletionVariants(@NotNull String text, int offset, @NotNull String prefix,
                                             @NotNull CompletionResultSet result) {

            result.addLookupAdvertisement("Select one or more users separated with comma, | or new lines");

            List<String> values = getAutocompleteValues();
            //   Arrays.asList("git commit -m", "check");

            for (String completionVariant : values) {
                final LookupElementBuilder element = LookupElementBuilder.create(completionVariant);
                result.addElement(element.withLookupString(completionVariant.toLowerCase()));
            }

        }

        private List<String> getAutocompleteValues() {
            List<String> values = new LinkedList<>();

            List<String> localBranches = getLocalBranches(project);
            values.add("git commit -m");
            values.add("git add --all");
            values.addAll(createMergeBranchValues(localBranches));
            values.addAll(createCheckoutValues(localBranches));
            return values;

        }

        private List<String> createMergeBranchValues(List<String> localBranches) {
            List<String> mergeBranchValues = new LinkedList<>();
            String mergeCommand = "git merge branch ";
            localBranches.forEach(branch -> mergeBranchValues.add(mergeCommand.concat(branch)));
            return mergeBranchValues;
        }

        private List<String> createCheckoutValues(List<String> localBranches) {
            List<String> checkoutValues = new LinkedList<>();
            String mergeCommand = "git checkout branch ";
            localBranches.forEach(branch -> checkoutValues.add(mergeCommand.concat(branch)));
            return checkoutValues;
        }

        private List<String> getLocalBranches(Project project) {
            List<String> localBranchesNames = new LinkedList<>();

            GitRepositoryManager manager = GitUtil.getRepositoryManager(project);
            List<GitRepository> repositories = manager.getRepositories();

            for (GitRepository repo : repositories) {
                repo.getBranches().getLocalBranches().forEach(b -> localBranchesNames.add(b.getName()));
            }

            return localBranchesNames;
        }
    }


}