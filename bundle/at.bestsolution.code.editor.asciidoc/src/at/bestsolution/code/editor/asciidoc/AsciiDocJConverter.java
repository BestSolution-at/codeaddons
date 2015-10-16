package at.bestsolution.code.editor.asciidoc;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.SafeMode;
import org.eclipse.fx.code.editor.asciidoc.HTMLConverter;
import org.osgi.service.component.annotations.Component;

@Component
public class AsciiDocJConverter implements HTMLConverter {
	private Asciidoctor doc;
	public AsciiDocJConverter() {
		doc = Asciidoctor.Factory.create();
	}
	@Override
	public CompletableFuture<String> convert(String filePath, String asciiDocSource) {
		Map<String, Object> options = OptionsBuilder.options()
                .compact(false)
                .headerFooter(true)
                .safe(SafeMode.UNSAFE)
                .backend("html")
                .asMap();
		return CompletableFuture.supplyAsync( () -> doc.convert(asciiDocSource, options));
	}

}
