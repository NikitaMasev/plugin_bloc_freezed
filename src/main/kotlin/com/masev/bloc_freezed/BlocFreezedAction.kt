package com.masev.bloc_freezed

import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.fileTypes.PlainTextFileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory

class BlocFreezedAction : AnAction() {

    private lateinit var dataContext: DataContext

    //blocName only CamelCase format
    override fun actionPerformed(e: AnActionEvent) {
        val blocName = Messages.showInputDialog(
            e.project,
            "Enter BLoC name (Like 'AuthorizationUser')",
            "Bloc Freezed Generator",
            Messages.getQuestionIcon()
        )

        blocName?.let { name -> handleBlocName(name) }
    }

    override fun update(e: AnActionEvent) {
        e.dataContext.let {
            this.dataContext = it
            val presentation = e.presentation
            presentation.isEnabled = true
        }
    }

    private fun handleBlocName(blocName: String) {
        val generators = BlocGeneratorFactory.getBlocGenerators(blocName)
        generate(generators, blocName)
    }

    private fun generate(mainSourceGenerators: List<BaseBlocGenerator>, dirName: String) {
        val project = CommonDataKeys.PROJECT.getData(dataContext)
        val view = LangDataKeys.IDE_VIEW.getData(dataContext)
        val directory = view?.orChooseDirectory?.createSubdirectory(dirName.camelToSnakeCase())

        ApplicationManager.getApplication().runWriteAction {
            CommandProcessor.getInstance().executeCommand(
                project,
                {
                    mainSourceGenerators.forEach { createSourceFile(project!!, it, directory!!) }
                },
                "Generate a new Bloc",
                null
            )
        }
    }

    private fun createSourceFile(project: Project, generator: BaseBlocGenerator, directory: PsiDirectory) {
        val fileName = generator.fileName()

        val fullPathToBloc = directory.virtualFile.path
        val indexStart = fullPathToBloc.indexOf(project.name)
        val importForBloc = fullPathToBloc.substring(indexStart).replace("/lib/", "/")

        var generatedFileData = generator.generate(importForBloc)
        val lineSeparator = System.getProperty("line.separator")

        if ("\n" != lineSeparator) {
            generatedFileData = generatedFileData.replace(lineSeparator, "\n")
        }

        val psiFile = PsiFileFactory.getInstance(project)
            .createFileFromText(fileName, PlainTextFileType.INSTANCE, generatedFileData)

        directory.add(psiFile)
    }

}