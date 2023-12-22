const { src, dest, watch, parallel } = require('gulp');

// CSS
const sass = require('gulp-sass')(require('sass'));
const plumber = require('gulp-plumber');
const autoprefixer = require('autoprefixer');
const cssnano = require('cssnano');
const postcss = require('gulp-postcss');
const sourcemaps = require('gulp-sourcemaps');

const css = (done) => {
	src('src/main/resources/static/scss/**/*.scss') // Identificar el archivo SASS
		// .pipe(sourcemaps.init())
		.pipe(plumber()) // ayuda a no detener el workflow cuando algo sale mal
		.pipe(sass()) // Compilarlo
		// .pipe(postcss([autoprefixer(), cssnano]))
		// .pipe(sourcemaps.write('.'))
		.pipe(dest('src/main/resources/static/css')); // Almacenarla en el disco duro

	done(); // Callback que avisa a gulp cuando llegamos al final
};

const dev = (done) => {
	watch('src/main/resources/static/scss/**/*.scss', css);
	done();
};

exports.css = css;
exports.dev = parallel(dev);
